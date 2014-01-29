using UnityEngine;
using System.Collections;

public class ObjectInSpace : MonoBehaviour {

	public ObjectInSpace other;
	// Use this for initialization
	void Start () {
		this.rigidbody.AddForce (new Vector3 (50, 10, -5) * 40);
	}
	
	// Update is called once per frame
	void FixedUpdate () {
		//m1 * m2 * g / (dist^2)
		float force = this.rigidbody.mass * other.rigidbody.mass * .5f / (Mathf.Pow((this.transform.position - other.transform.position).magnitude, 2));
		this.rigidbody.AddForce ((other.transform.position - this.transform.position).normalized * force);
		print ((other.transform.position - this.transform.position).normalized * force);
	}
}
