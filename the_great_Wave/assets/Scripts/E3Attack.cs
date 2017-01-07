using UnityEngine;
using System.Collections;

public class E3Attack : MonoBehaviour
{
    public float speed;
    public float rotSpeed = 90f;
    public float proxlim;
    

    Transform player;
    public GameObject bulletPrefab;


    void Start()
    {
        InvokeRepeating("Shoot", 0, 6);
        proxlim = Random.Range(300, 600);
        proxlim = proxlim / 100;
    }

    // Update is called once per frame
    void Update()
    {
        
        if (player == null)
        {
            // Find the player's ship!
            GameObject go = GameObject.FindWithTag("Player");

            if (go != null)
            {
                player = go.transform;
            }
        }

        // At this point, we've either found the player,
        // or he/she doesn't exist right now.

        if (player == null)
            return; // Try again next frame!

        // HERE -- we know for sure we have a player. Turn to face it!

        Vector3 dir = player.position - transform.position;
        double distance = System.Math.Sqrt(dir.x * dir.x + dir.y * dir.y);

        //If enemy is at proximity limit, change course to circle the player
        if (distance < proxlim)
        {
            float temp = dir.x;
            dir.x = dir.y * -1;
            dir.y = temp;
        }

        dir.Normalize();

        float zAngle = Mathf.Atan2(dir.y, dir.x) * Mathf.Rad2Deg - 90;

        Quaternion desiredRot = Quaternion.Euler(0, 0, zAngle);

        transform.rotation = Quaternion.RotateTowards(transform.rotation, desiredRot, rotSpeed * Time.deltaTime);

        transform.position += dir * speed * Time.deltaTime;
    }

    void OnCollisionEnter2D(Collision2D col)
    {
        if (col.gameObject.tag == "Player")
        {
            Debug.Log("Enemy Collide");
        }

    }

    void Shoot()
    {

        Vector3 dir = player.position - transform.position;
        dir.Normalize();

        GameObject bulletGO = (GameObject)Instantiate(bulletPrefab, transform.position, transform.rotation);
        Rigidbody2D projectile = bulletGO.GetComponent<Rigidbody2D>();
        projectile.velocity = dir * 10;

    }
}
