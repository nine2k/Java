using UnityEngine;
using System.Collections;

public class FreezeEffect : MonoBehaviour {

    public float scaleRate = 0.001f;


	// Use this for initialization
	void Start () {
        transform.localScale = Vector3.one * 0.3f;
        Invoke("unfreeze", 2);
    }

    // Update is called once per frame
    void Update() {
        if (transform.localScale[0] < 0.3)
        {
            transform.localScale += Vector3.one * scaleRate;
        }
    }

    void unfreeze()
    {
        Destroy(transform.gameObject);
    }
}
