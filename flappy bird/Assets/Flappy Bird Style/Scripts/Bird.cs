using UnityEngine;
using System.Collections;

public class Bird : MonoBehaviour 
{
	public float upForce;					
	private bool isDead = false;

	private Animator anim;					
	private Rigidbody2D rb2d;				

	void Start()
	{
		
		anim = GetComponent<Animator> ();

		rb2d = GetComponent<Rigidbody2D>();
	}

	void Update()
	{
		
		if (isDead == false) 
		{
			
			if (Input.GetMouseButtonDown(0)) 
			{
				
				anim.SetTrigger("Flap");


				//rb2d.velocity = Vector2.zero;

				//rb2d.AddForce(new Vector2(0, upForce));

			
				rb2d.velocity = new Vector2 (rb2d.velocity.x, 3);


			}
		}
	}

	void OnCollisionEnter2D(Collision2D other)
	{
		
		rb2d.velocity = Vector2.zero;

		isDead = true;

		anim.SetTrigger ("Die");

		GameControl.instance.BirdDied ();
	}
}

////////////////////////////////////////////////////////////////////////

//void Start () {
//	rb = GetComponent<Rigidbody2D> ();
//}

// Update is called once per frame
//void Update () {
//	rb.velocity = new Vector2 (1, rb.velocity.y);
//	onGround = Physics2D.OverlapCircle (groundCheck.position,groundCheckRadius,whatIsGround);
//
//	if (Input.GetMouseButtonDown (0)) {
//		rb.velocity = new Vector2 (rb.velocity.x, 3);
//	}
//
//}
