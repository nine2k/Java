using UnityEngine;
using System.Collections;

public class FreezeRing : MonoBehaviour {

    public float size = 0.01f;
    public float maxScale = 0.5f;
    public float scaleRate = 0.008f;
    public Transform parent;


	// Use this for initialization
	void Start () {

        //The ring is initially small
        transform.localScale = Vector3.one * size;
        GameObject[] enemies = GameObject.FindGameObjectsWithTag("Enemy");
        Transform temp;
        parent = enemies[0].transform;

        //The enemy closest to the ring is the parent of the ring
        for (int x = 0; x < enemies.Length; x++)
        {
            //Get distance from next enemy
            temp = enemies[x].transform;
            float xdif = Mathf.Abs(temp.position.x - transform.position.x);
            float ydif = Mathf.Abs(temp.position.y - transform.position.y);
            float distance = Mathf.Sqrt(xdif * xdif + ydif * ydif);

            //get distance from closest
            float xdif2 = Mathf.Abs(parent.position.x - transform.position.x);
            float ydif2 = Mathf.Abs(parent.position.y - transform.position.y);
            float distance2 = Mathf.Sqrt(xdif2 * xdif2 + ydif2 * ydif2);

            //if distance from next enemy is shorter than closest, it is the new closest
            if (distance < distance2)
            {
                parent = temp;
            }
        }

    }
	
	// Update is called once per frame
	void Update ()
    {

        //Destroy the ring once it reaches it max size
	    if (transform.localScale.x > maxScale)
        {
            Destroy(transform.gameObject);
        }

        else
        {
            //Grow and spin the ring
            this.transform.Rotate(new Vector3(0, 0, 1000f * Time.deltaTime));
            transform.localScale += Vector3.one * scaleRate;

            //Keep the ring around its parent
            if (parent != null)
            {
                transform.position = parent.position;
            }
           
           

        }

	}
}
