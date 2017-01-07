using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class ScoreManager1 : MonoBehaviour {

    
    public int score;

    public Text theText;

    // Use this for initialization
    void Start()
    {

        theText = GetComponent<Text>();
        score = 0;
    }

    // Update is called once per frame
    void Update()
    {
        theText.text = "SCORE: " + score.ToString();

    }

    public void IncreaseScore()
    {
        score = score + 100;
    }

}
