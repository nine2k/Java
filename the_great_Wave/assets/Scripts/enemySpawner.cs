using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class enemySpawner : MonoBehaviour {
    public float theTimer;
    public GameObject[] enemies;
    public Transform enemy2;
    public Transform enemy3;
    public Transform enemy4;
    public Transform enemy5;
    public Transform levelUp;
    public GameObject[] clones;
    public Text levelText;
    public AudioSource incoming;
   

    public int amount;
    public int level;
    public bool allSpawned = false;

    private Vector2 spawnPoint;

    
    
    // Use this for initialization
    void Start () {
        level = 1;
        levelSpawn();

    }

    
    IEnumerator levelSpawn()
    {
		
        if (level < 4)
        {
            for (int x = 0; x < level; x++)
            {
                StartCoroutine(spawnEnemy(enemy2, 2));

            }
        }

		if (level == 4) {
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));

		}
		if (level == 5) {
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));

		}
		if (level == 6) {
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));


		}
		if (level == 7) {
			StartCoroutine(spawnEnemy(enemy3, 2));
			StartCoroutine(spawnEnemy(enemy3, 2));

		}

		if (level == 8) {
			StartCoroutine(spawnEnemy(enemy3, 2));
			StartCoroutine(spawnEnemy(enemy3, 2));
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy2, 2));

		}
		if (level == 9) {
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy3, 2));

		}
		if (level == 10) {
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy3, 2));

		}
		if (level == 11) {
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy3, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy3, 2));

		}
		if (level == 12) {
			StartCoroutine(spawnEnemy(enemy3, 2));
			StartCoroutine(spawnEnemy(enemy3, 2));
			StartCoroutine(spawnEnemy(enemy3, 2));
			StartCoroutine(spawnEnemy(enemy3, 2));


		}
		if (level == 13) {
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));

		}
		if (level == 14) {
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));

		}
		if (level == 15) {
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy2, 2));
			StartCoroutine(spawnEnemy(enemy4, 2));


		}
		if (level == 16) {
			StartCoroutine(spawnEnemy(enemy3, 2));
			StartCoroutine(spawnEnemy(enemy3, 2));
			StartCoroutine(spawnEnemy(enemy3, 2));
			StartCoroutine(spawnEnemy(enemy3, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));


		}
		if (level == 17) {
			StartCoroutine(spawnEnemy(enemy3, 2));
			StartCoroutine(spawnEnemy(enemy3, 2));
			StartCoroutine(spawnEnemy(enemy3, 2));
			StartCoroutine(spawnEnemy(enemy3, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));
		}
		if (level == 18) {
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy4, 2));
			StartCoroutine(spawnEnemy(enemy5, 2));


		}


        if (level > 18)
        {
                
			for (int x = 0; x < level/9; x++)
            {
				StartCoroutine(spawnEnemy(enemy3, 2));
				StartCoroutine(spawnEnemy(enemy3, 2));
				StartCoroutine(spawnEnemy(enemy3, 2));
            }
        }

        

        //for (int x = 0; x < level/2; x++)
        //{
           // StartCoroutine(spawnEnemy(enemy3, 2));
          //  StartCoroutine(spawnEnemy(enemy4, 2));
          //  StartCoroutine(spawnEnemy(enemy5, 2));
       // }

        StartCoroutine(spawned());
        return null;
    }

    IEnumerator spawned()
    {
        yield return new WaitForSeconds(level + 2);
        print("True!");
        allSpawned = true;
        //return null;
    }

	// Update is called once per frame
	void Update () {
        enemies = GameObject.FindGameObjectsWithTag("Enemy");
        amount = enemies.Length;

        if (amount == 0 && allSpawned)
        {
            level++;
            levelText.text = "LEVEL: " + level.ToString();
            allSpawned = false;

            clones = GameObject.FindGameObjectsWithTag("playerbullet");
            for (int x = 0; x < clones.Length; x++)
            {
                Destroy(clones[x], 2);
            }

            Instantiate(levelUp, new Vector2(0,0), Quaternion.identity);
            clones = GameObject.FindGameObjectsWithTag("LevelUp");
            AudioSource audio = GetComponent<AudioSource>();
            audio.Play();
            for (int x = 0; x < clones.Length; x++)
            {
                Destroy(clones[x], 3);
            }
            
            Invoke("levelSpawn",3);   
        }

    }

    IEnumerator spawnEnemy(Transform newEnemy, int waitTime)
    {
        yield return new WaitForSeconds(waitTime);

        spawnPoint.x = Random.Range(-1, 1);
        spawnPoint.y = Random.Range(-1, 1);

       
        incoming.Play();

        Instantiate(newEnemy, spawnPoint, Quaternion.identity);
        CancelInvoke();

    }
}