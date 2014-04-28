/*
 * $Header: /l/extreme/cvs/codes/XPP3/java/src/java/xpath/org/xmlpull/v1/builder/xpath/jaxen/expr/DefaultFunctionCallExpr.java,v 1.2 2005/08/11 22:44:00 aslom Exp $
 * $Revision: 1.2 $
 * $Date: 2005/08/11 22:44:00 $
 *
 * ====================================================================
 *
 * Copyright (C) 2000-2002 bob mcwhirter & James Strachan.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions, and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions, and the disclaimer that follows 
 *    these conditions in the documentation and/or other materials 
 *    provided with the distribution.
 *
 * 3. The name "Jaxen" must not be used to endorse or promote products
 *    derived from this software without prior written permission.  For
 *    written permission, please contact license@jaxen.org.
 * 
 * 4. Products derived from this software may not be called "Jaxen", nor
 *    may "Jaxen" appear in their name, without prior written permission
 *    from the Jaxen Project Management (pm@jaxen.org).
 * 
 * In addition, we request (but do not require) that you include in the 
 * end-user documentation provided with the redistribution and/or in the 
 * software itself an acknowledgement equivalent to the following:
 *     "This product includes software developed by the
 *      Jaxen Project (http://www.jaxen.org/)."
 * Alternatively, the acknowledgment may be graphical using the logos 
 * available at http://www.jaxen.org/
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE Jaxen AUTHORS OR THE PROJECT
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 * ====================================================================
 * This software consists of voluntary contributions made by many 
 * individuals on behalf of the Jaxen Project and was originally 
 * created by bob mcwhirter <bob@werken.com> and 
 * James Strachan <jstrachan@apache.org>.  For more information on the 
 * Jaxen Project, please see <http://www.jaxen.org/>.
 * 
 * $Id: DefaultFunctionCallExpr.java,v 1.2 2005/08/11 22:44:00 aslom Exp $
 */



package org.xmlpull.v1.builder.xpath.jaxen.expr;

import org.xmlpull.v1.builder.xpath.jaxen.Context;
import org.xmlpull.v1.builder.xpath.jaxen.Function;
import org.xmlpull.v1.builder.xpath.jaxen.JaxenException;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

class DefaultFunctionCallExpr extends DefaultExpr implements FunctionCallExpr
{
    private String prefix;
    private String functionName;
    private List   parameters;

    public DefaultFunctionCallExpr(String prefix,
                                   String functionName)
    {
        this.prefix       = prefix;
        this.functionName = functionName;
        this.parameters   = new ArrayList();
    }

    public void addParameter(Expr parameter)
    {
        this.parameters.add( parameter );
    }

    public List getParameters()
    {
        return this.parameters;
    }

    public String getPrefix()
    {
        return this.prefix;
    }

    public String getFunctionName()
    {
        return this.functionName;
    }

    public String getText()
    {
        StringBuffer buf = new StringBuffer();

        String prefix = getPrefix();

        if ( prefix != null
             &&
             ! prefix.equals("") )
        {
            buf.append( prefix );
            buf.append( ":" );
        }

        buf.append( getFunctionName() );
        buf.append( "(" );

        Iterator paramIter = getParameters().iterator();
        Expr     eachParam = null;

        while ( paramIter.hasNext() )
        {
            eachParam = (Expr) paramIter.next();

            buf.append( eachParam.getText() );

            if ( paramIter.hasNext() )
            {
                buf.append( ", " );
            }
        }

        buf.append( ")" );
        
        return buf.toString();
    }

    public Expr simplify()
    {
        List paramExprs = getParameters();
        int  paramSize  = paramExprs.size();
        Expr eachParam  = null;

        List newParams  = new ArrayList( paramSize );

        for ( int i = 0 ; i < paramSize ; ++i )
        {
            eachParam = (Expr) paramExprs.get( i );

            newParams.add( eachParam.simplify() );
        }

        this.parameters = newParams;

        return this;
    }

    public String toString()
    {
        String prefix = getPrefix();

        if ( prefix == null )
        {
            return "[(DefaultFunctionCallExpr): " + getFunctionName() + "(" + getParameters() + ") ]";
        }

        return "[(DefaultFunctionCallExpr): " + getPrefix() + ":" + getFunctionName() + "(" + getParameters() + ") ]";
    }

    public Object evaluate(Context context) throws JaxenException
    {
        String namespaceURI =
            context.translateNamespacePrefixToUri( getPrefix() );

        Function func = context.getFunction( namespaceURI,
                                             getPrefix(),
                                             getFunctionName() );

        List paramExprs = getParameters();
        int  paramSize  = paramExprs.size();
        
        List paramValues = new ArrayList( paramSize );
        Expr eachParam   = null;
        Object eachValue = null;
        
        for ( int i = 0 ; i < paramSize ; ++i )
        {
            eachParam = (Expr) paramExprs.get( i );
            
            eachValue = eachParam.evaluate( context );
            
            paramValues.add( eachValue );
        }
        
        return func.call( context, paramValues );
    }
}