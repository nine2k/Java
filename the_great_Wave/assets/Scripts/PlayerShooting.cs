using UnityEngine;
using System.Collections;

public class PlayerShooting : MonoBehaviour
{
    public gameMaster gm;
    public GameObject bulletPrefab;
    public float fireRate = 2f;
    public float timeToFire = 0;
    public float speed = 0;
    public float damage = 10;
    public Transform firePoint;
    public Vector3 firePoint2;
    public LayerMask whatToHit;
    private LifeManager life;

    void Start()
    {
        life = FindObjectOfType<LifeManager>();
    }
    
    void Awake()
    {
        firePoint = transform.FindChild("FirePoint");
        if (firePoint == null)
        {
            Debug.LogError("No firepoint!");

        }
    }

    // Update is called once per frame
    void Update()
    {
        RayCastLine();


        if (fireRate == 0)
        {
            if (Input.GetButtonDown("Fire1"))
            {
                Shoot();
                LifeManager.lifeCounter--;
            }
        }
        else
        {
            if (Input.GetButton ("Fire1") && Time.time > timeToFire)
            {
                timeToFire = Time.time + 1 / fireRate;
                Shoot();
                
            }
        }
        

        
    }

    void RayCastLine()

    {
        Vector2 mousePosition = new Vector2(Camera.main.ScreenToWorldPoint(Input.mousePosition).x, Camera.main.ScreenToWorldPoint(Input.mousePosition).y);
        Vector2 firePointPosition = new Vector2(firePoint.position.x, firePoint.position.y);
        RaycastHit2D hit = Physics2D.Raycast(firePointPosition, mousePosition - firePointPosition, 100, whatToHit);
        Debug.DrawLine(firePointPosition, (mousePosition - firePointPosition) * 100);
        if (hit.collider != null)
        {
            Debug.DrawLine(firePointPosition, hit.point, Color.red);
        }
    }

    void Shoot()
    {
        //cooldownTimer -= Time.deltaTime;

        AudioSource audio = GetComponent<AudioSource>();
        audio.Play();
        //cooldownTimer = fireDelay;

      
        Vector3 offset = transform.rotation * firePoint2;
        Vector3 sp = Camera.main.WorldToScreenPoint(transform.position);
        Vector3 dir = (Input.mousePosition - sp).normalized;
        GameObject bulletGO = (GameObject)Instantiate(bulletPrefab, transform.position + offset, transform.rotation);
        Rigidbody2D projectile = bulletGO.GetComponent<Rigidbody2D>();
        projectile.velocity = dir * speed;

    }
}


