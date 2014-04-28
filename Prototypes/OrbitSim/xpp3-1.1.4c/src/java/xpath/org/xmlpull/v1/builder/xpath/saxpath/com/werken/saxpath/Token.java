/*
 * $Header: /l/extreme/cvs/codes/XPP3/java/src/java/xpath/org/xmlpull/v1/builder/xpath/saxpath/com/werken/saxpath/Token.java,v 1.1 2004/06/16 15:55:43 aslom Exp $
 * $Revision: 1.1 $
 * $Date: 2004/06/16 15:55:43 $
 *
 * ====================================================================
 *
 * Copyright (C) 2000-2002 werken digital.
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
 * 3. The name "SAXPath" must not be used to endorse or promote products
 *    derived from this software without prior written permission.  For
 *    written permission, please contact license@saxpath.org.
 * 
 * 4. Products derived from this software may not be called "SAXPath", nor
 *    may "SAXPath" appear in their name, without prior written permission
 *    from the SAXPath Project Management (pm@saxpath.org).
 * 
 * In addition, we request (but do not require) that you include in the 
 * end-user documentation provided with the redistribution and/or in the 
 * software itself an acknowledgement equivalent to the following:
 *     "This product includes software developed by the
 *      SAXPath Project (http://www.saxpath.org/)."
 * Alternatively, the acknowledgment may be graphical using the logos 
 * available at http://www.saxpath.org/
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE SAXPath AUTHORS OR THE PROJECT
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
 * individuals on behalf of the SAXPath Project and was originally 
 * created by bob mcwhirter <bob@werken.com> and 
 * James Strachan <jstrachan@apache.org>.  For more information on the 
 * SAXPath Project, please see <http://www.saxpath.org/>.
 * 
 * $Id: Token.java,v 1.1 2004/06/16 15:55:43 aslom Exp $
 */



package org.xmlpull.v1.builder.xpath.saxpath.com.werken.saxpath;

class Token
{
    private int    tokenType;
    private String parseText;
    private int    tokenBegin;
    private int    tokenEnd;

    public Token(int tokenType,
                 String parseText,
                 int tokenBegin,
                 int tokenEnd)
    {
        setTokenType( tokenType );
        setParseText( parseText );
        setTokenBegin( tokenBegin );
        setTokenEnd( tokenEnd );
    }

    private void setTokenType(int tokenType)
    {
        this.tokenType = tokenType;
    }

    public int getTokenType()
    {
        return this.tokenType;
    }

    private void setParseText(String parseText)
    {
        this.parseText = parseText;
    }

    public String getTokenText()
    {
        return this.parseText.substring( getTokenBegin(),
                                         getTokenEnd() );
    }

    private void setTokenBegin(int tokenBegin)
    {
        this.tokenBegin = tokenBegin;
    }

    int getTokenBegin()
    {
        return this.tokenBegin;
    }

    private void setTokenEnd(int tokenEnd)
    {
        this.tokenEnd = tokenEnd;
    }

    int getTokenEnd()
    {
        return this.tokenEnd;
    }

    public String toString()
    {
        return ("[ (" + tokenType + ") (" + getTokenText() + ")");
    }
}