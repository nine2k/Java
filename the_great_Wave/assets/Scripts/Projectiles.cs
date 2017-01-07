using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class Projectiles : MonoBehaviour {

    public int score;
    public Text countText;
    public GameObject myPrefab;
    private LifeManager life;
    public float timeinvul;
    public bool invul;
    public float respawn;

    // Use this for initialization
    void Start () {
        respawn = 2;
   

    }
	
	// Update is called once per frame
	void Update () {
        respawn -= Time.deltaTime;
        
    }

 

    void OnCollisionEnter2D(Collision2D col)
    {
        if (col.gameObject.tag == "Enemy")
        {

            Destroy(col.gameObject);
            Destroy(this.gameObject);



        }

        if (col.gameObject.tag == "Player")
        {


            if (respawn < 0)
            {
                Destroy(col.gameObject);
                Destroy(this.gameObject);
                LifeManager.lifeCounter--;

                if (LifeManager.lifeCounter > 0)
                {
                    GameObject go = (GameObject)Instantiate(myPrefab);
                    Debug.Log("Boom");
                    respawn = 2;

                }
            }
         
        }


    }
}
