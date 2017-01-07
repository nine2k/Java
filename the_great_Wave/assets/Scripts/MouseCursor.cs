using UnityEngine;
using System.Collections;

public class MouseCursor : MonoBehaviour
{
    public Texture cursorImage;
    void OnGUI()
    {
        Vector3 mousePos = Input.mousePosition;
        Rect pos = new Rect(mousePos.x - 20, Screen.height - mousePos.y - 30, cursorImage.width, cursorImage.height);
        GUI.Label(pos, cursorImage);

    }

void Start()
    {
        Cursor.visible = false;
    }

}
