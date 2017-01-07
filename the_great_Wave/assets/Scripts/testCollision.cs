using UnityEngine;
using System.Collections;

public class testCollision : MonoBehaviour {


    void OnCollisionEnter2D(Collision2D col)
    {
        Debug.Log("WALL HIT");
       
    }
}
