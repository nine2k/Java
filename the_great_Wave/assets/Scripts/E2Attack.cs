using UnityEngine;
using System.Collections;

public class E2Attack : MonoBehaviour
{
    public float speed;
    public float rotSpeed = 90f;
    public double offset;

    Transform player;

    void Start()
    {
        //create a random offset for each enemy
        //This prevents them from clumping up
        int newoff = Random.Range(-60, 60);
        offset = newoff / 100.0;
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

        //Add a movement offset multiplied by the distance from the player
        //This will make enemies spiral in at varying angles
        float distance = Mathf.Sqrt(dir.x * dir.x + dir.y * dir.y);
        if (distance != 0)
        {
            dir.x = dir.x - (float)offset * distance;
            dir.y = dir.y + (float)offset * distance;
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
}
