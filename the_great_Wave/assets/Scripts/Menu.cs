using UnityEngine;
using System.Collections;

public class Menu : MonoBehaviour {

	public string startLevel = "test";

	public void NewGame()
	{
		Application.LoadLevel(startLevel);

	}

    public void Start()
    {
        Cursor.visible = true;
    }

	public void QuitGame()
	{
		Debug.Log ("Game Exited");
		Application.Quit();
	}
}
