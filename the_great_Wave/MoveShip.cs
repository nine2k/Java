using UnityEngine;
using System.Collections;

public class MoveShip : MonoBehaviour {

	public float speed = 2.5f;
	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKey (KeyCode.A)) {
			Vector2 pos = transform.position;
			pos.x -= speed * Time.deltaTime;
			transform.position = pos;
		}//end if

		if (Input.GetKey (KeyCode.D)) {
			Vector2 pos = transform.position;
			pos.x += speed * Time.deltaTime;
			transform.position = pos;
		}//end if

		if (Input.GetKey (KeyCode.W)) {
			transform.position += Vector3.up * speed * Time.deltaTime;
		}//end if

		if (Input.GetKey (KeyCode.S)) {
			transform.position += Vector3.down * speed * Time.deltaTime;
		}//end if

	}
}
