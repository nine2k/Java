using UnityEngine;
using System.Collections;

[ExecuteInEditMode]
[RequireComponent(typeof(Rigidbody2D), typeof(EdgeCollider2D))]

public class circleContainer : MonoBehaviour
{
    public float Radius = 1.0f;
    public int NumPoints = 32;

    EdgeCollider2D EdgeCollider;
    float CurrentRadius = 0.0f;

  
    void Start()
    {
        CreateCircle();
    }


    void OnCollisionEnter2D(Collision2D col)
    {
        if (col.gameObject.name == "PlayerBulletPrefab 1 1")
        {
            Debug.Log("HIT!");
        }
        

    }

    void Update()
    {
        // If the radius or point count has changed, update the circle
        if (NumPoints != EdgeCollider.pointCount || CurrentRadius != Radius)
        {
            CreateCircle();
        }

    }

    void CreateCircle()
    {
        Vector2[] edgePoints = new Vector2[NumPoints + 1];
        EdgeCollider = GetComponent<EdgeCollider2D>();

        for (int loop = 0; loop <= NumPoints; loop++)
        {
            float angle = (Mathf.PI * 2.0f / NumPoints) * loop;
            edgePoints[loop] = new Vector2(Mathf.Sin(angle), Mathf.Cos(angle)) * Radius;
        }

        EdgeCollider.points = edgePoints;
        CurrentRadius = Radius;
    }

    
    public static Vector2 GetRayExitThroughCircle(CircleCollider2D circleCollider, Vector2 entryPoint, Vector2 direction)
    {
        Vector2 colliderCenter = circleCollider.transform.position;
        Vector2 directionToCenter = colliderCenter - entryPoint;

        float angleTowardsCenter = Vector2.Angle(direction, directionToCenter);
        float angleTowardsExitPoint = 180 - (angleTowardsCenter + angleTowardsCenter); // Angles inside a triangle add to 180

        if (Vector3.Cross(direction, directionToCenter).z < 0)
        {
            angleTowardsExitPoint *= -1;
        }

        Vector2 directionToExitPoint = Quaternion.Euler(0, 0, angleTowardsExitPoint) * directionToCenter;
        Vector2 exitPoint = colliderCenter + (-directionToExitPoint.normalized * circleCollider.radius);

        return exitPoint;


    }
}
