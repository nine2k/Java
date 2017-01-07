using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class LifeManager : MonoBehaviour {

    public int startingLives;
    public static int lifeCounter;
    public GameObject gameOverScreen;
    public float waitAfterGameOver;


    public Text theText;

	// Use this for initialization
	void Start () {

        theText = GetComponent<Text>();
        lifeCounter = startingLives;
        waitAfterGameOver = 5;

        
	}
	
	// Update is called once per frame
	void Update () {
        theText.text = "LIVES: " + lifeCounter.ToString(); 
        
        if (lifeCounter < 0)
        {
            lifeCounter = 0;
        }

        if (lifeCounter <= 0)
        {
            gameOverScreen.SetActive(true);
            Time.timeScale = 0;

            if (gameOverScreen.activeSelf)
            {
                waitAfterGameOver -= Time.deltaTime;
            }

            if (waitAfterGameOver < 0)
            {
               Application.LoadLevel("title_menu");
            }
        }  
	
	}

    public void TakeLife()
    {
        lifeCounter--;
    }

}
