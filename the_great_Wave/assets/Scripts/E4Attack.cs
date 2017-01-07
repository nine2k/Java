using UnityEngine;
using System.Collections;

public class E4Attack : MonoBehaviour
{
    public float speed;
    public float rotSpeed = 90f;
    public Vector2 goal;
    public Vector3 dir;
    public GameObject attackPrefab;
    public float radius = 4.5f;


    Transform player;

    void Start()
    {
        float hyp = Random.Range(0, radius);
        float angle = Random.Range(0, 360);
        goal.x = hyp*Mathf.Cos(angle);
        goal.y = hyp*Mathf.Sin(angle);

        InvokeRepeating("Shoot", 0, 5);
    }

    // Update is called once per frame
    void Update()
    {
        dir.x = goal.x - transform.position.x;
        dir.y = goal.y - transform.position.y;
        
        if (dir.x < 0.1 && dir.y < 0.1)
        {
            //Generate random radius
           float hyp = Random.Range(0, radius*100)/100;

            //Generate random direction
            goal.x = Random.Range(-100, 100);
            goal.y = Random.Range(-100, 100);
            goal.Normalize();
            
            //Generate point a random distance in a random direction
            goal.x = goal.x * hyp;
            goal.y = goal.y * hyp;


            dir.x = goal.x - transform.position.x;
            dir.y = goal.y - transform.position.y;
        }

        dir.Normalize();

        float zAngle = Mathf.Atan2(dir.y, dir.x) * Mathf.Rad2Deg - 90;
        Quaternion desiredRot = Quaternion.Euler(0, 0, zAngle);
        transform.rotation = Quaternion.RotateTowards(transform.rotation, desiredRot, rotSpeed * Time.deltaTime);
        transform.position += dir * speed * Time.deltaTime;
    }

    void Shoot()
    {
        GameObject freezeAttack = (GameObject)Instantiate(attackPrefab, transform.position, transform.rotation);
    }

    void OnCollisionEnter2D(Collision2D col)
    {
        if (col.gameObject.tag == "Player")
        {
            Debug.Log("Enemy Collide");
        }

    }
}
