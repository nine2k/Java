using UnityEngine;
using System.Collections;

public class gameMaster : MonoBehaviour {

    //public GUIText pointsText;
   
    public GUIText scoreText;
    public int score;

    // Use this for initialization
	
	// Update is called once per frame
	void Update () {
        //pointsText.text = ("Score: " + score);
	
	}

    void Start()
    {
        score = 0;
        UpdateScore();
    }

    public void AddScore (int newScoreValue)
    {
        score += newScoreValue;
        UpdateScore();
    }

    public void UpdateScore()
    {
        scoreText.text = "Score: " + score;
    }

    
}
