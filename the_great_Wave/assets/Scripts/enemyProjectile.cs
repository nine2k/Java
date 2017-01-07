using UnityEngine;
using System.Collections;

using UnityEngine;
using UnityEngine.UI;
using System.Collections;
public class enemyProjectile : MonoBehaviour {
    public LifeManager life;
    public float timeinvul;
    public bool invul;

    // Use this for initialization
    void Start () {
        timeinvul = 3;
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

    void OnCollisionEnter2D(Collision2D col)
    {
        if (col.gameObject.tag == "Player")
        {
           
                invul = true;
                Debug.Log("Player hit");
                Destroy(col.gameObject);
                LifeManager.lifeCounter--;
               
                if (LifeManager.lifeCounter > 0)
                {
                    GameObject go = (GameObject)Instantiate(Resources.Load("Player 1"));
                  
                }
            
        }

    }
}