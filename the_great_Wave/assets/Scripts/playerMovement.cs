using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class playerMovement : MonoBehaviour
{

    public float maxSpeed = 10f;
    public float rotSpeed = 180f;
	public float acceleration = 2.5f;
    public float deacceleration = 2.5f;
    public float speed = 4;
    public ParticleSystem particle;
    private bool isEmitting;
    public Text scoreText;
    public int score;
    public GameObject frozenPrefab;
    public float freezeTime = 2f;
    public Rigidbody2D rigid;
    public bool invul;
    public GameObject myPrefab;
    public float timeinvul;
   



    void Start(){
        timeinvul = 2;
        invul = true;
    
        
       
    }
   

    void FixedUpdate()
    {

        timeinvul -= Time.deltaTime;
        if (timeinvul < 0)
        {
            invul = false;
        }

        //ParticleSystem particle = GetComponent<ParticleSystem>();
        //particle.Play();
        // Moves ship relative to mouse position
        rigid = this.GetComponent<Rigidbody2D>();
        RotateShip();
        Quaternion rot = transform.rotation;   
        float z = rot.eulerAngles.z; 
        rot = Quaternion.Euler(0, 0, z);
        transform.rotation = rot;

        // Movement

        //Freeze player if inside freeze ring
        //(collision detection wouldnt work right)
        GameObject[] freezeRings = GameObject.FindGameObjectsWithTag("FreezeRing");
        for (int x = 0; x < freezeRings.Length; x++)
        {
            //Find distance from center of ring, and radius of ring
            Vector3 ringSize = freezeRings[x].transform.localScale;
            float radius = (ringSize[0] / 2)*6;
            Vector3 position = freezeRings[x].transform.position;
            Vector3 difference = transform.position - position;
            float distance = Mathf.Sqrt(difference[0] * difference[0] + difference[1] * difference[1]);
            
            //Freeze the player for 2 seconds if player is inside radius
            if (distance < radius && speed != 0)
            {
                GameObject freezeAttack = (GameObject)Instantiate(frozenPrefab, transform.position, transform.rotation);
                speed = 0;
                Invoke("unfreeze", freezeTime);
            }
        }

        // LEFT
        if (Input.GetKey(KeyCode.A))
        {
            rigid.AddForce(Vector2.left * speed);
            //transform.position += Vector3.left * speed * Time.deltaTime;
        }

        // RIGHT
        if (Input.GetKey (KeyCode.D)) {
            rigid.AddForce(Vector2.right * speed);
            //transform.position += Vector3.right * speed * Time.deltaTime;
        }

        // UP
        if (Input.GetKey (KeyCode.W)) {
            rigid.AddForce(Vector2.up * speed);
            //transform.position += Vector3.up * speed * Time.deltaTime;
        }


        // DOWN
        if (Input.GetKey (KeyCode.S)) {
            rigid.AddForce(Vector2.down * speed);
            // transform.position += Vector3.down * speed * Time.deltaTime;
        }
 


    }

    IEnumerator respawn()
    {
        yield return new WaitForSeconds(5);
    }

    void OnCollisionEnter2D(Collision2D col)
    {
        if (col.gameObject.tag == "Enemy")
        {
            if (invul == false)
            {
              
                Debug.Log("Player hit");
                AudioSource audio = GetComponent<AudioSource>();
                audio.Play();
                Destroy(this.gameObject);
                LifeManager.lifeCounter--;

                
                if (LifeManager.lifeCounter != 0)
                {
                    GameObject go = (GameObject)Instantiate(Resources.Load("Player 1"));
                    timeinvul -= Time.deltaTime;
                    
                }
            }

        }

    }

    void unfreeze()
    {
        speed = 4;
    }

       private void RotateShip()
    {
        
            //Grab mouse position

            Vector2 mouse = Camera.main.ScreenToViewportPoint(Input.mousePosition);        //Mouse position
            Vector3 objpos = Camera.main.WorldToViewportPoint(transform.position);        //Object position on screen
            Vector2 relobjpos = new Vector2(objpos.x - 0.5f, objpos.y - 0.5f);            //Set coordinates relative to object
            Vector2 relmousepos = new Vector2(mouse.x - 0.5f, mouse.y - 0.5f) - relobjpos;

            float angle = Vector2.Angle(Vector2.up, relmousepos);    //Angle calculation
            if (relmousepos.x > 0)
                angle = 360 - angle;
            Quaternion quat = Quaternion.identity;
            quat.eulerAngles = new Vector3(0, 0, angle); //Changing angle
            transform.rotation = quat;      
    }

}

