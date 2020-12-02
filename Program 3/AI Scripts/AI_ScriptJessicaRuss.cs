using UnityEngine;
using System.Collections;

public class AI_ScriptJessicaRuss : MonoBehaviour {

    public CharacterScript mainScript;

    public float[] bombSpeeds;
    public float[] buttonCooldowns;
    public float playerSpeed;
    public int[] beltDirections;
    public float[] buttonLocations;
	public float[] bombLocations;
	 public float charLocation;
    

	// Use this for initialization
	void Start () {
        mainScript = GetComponent<CharacterScript>();

        if (mainScript == null)
        {
            print("No CharacterScript found on " + gameObject.name);
            this.enabled = false;
        }

        buttonLocations = mainScript.getButtonLocations();

        playerSpeed = mainScript.getPlayerSpeed();
	}

	// Update is called once per frame
	void Update () {
		
		 buttonCooldowns = mainScript.getButtonCooldowns();
        beltDirections = mainScript.getBeltDirections();
        
	
        //Your AI code goes here

        float minDistance = 1000; 
		int minIndex = 0;
		float curDistance;

		bombSpeeds = mainScript.getBombSpeeds();


        int targetBeltIndex =0;
        
        int beltLength =  beltDirections.Length;

        bombLocations = mainScript.getBombDistances();
        charLocation = mainScript.getCharacterLocation();

		
		
        //if the button location of the target is greater than the character location aka above it
        if (buttonLocations[targetBeltIndex] > charLocation)
		{
            //move up and push the button
            mainScript.moveUp();
			mainScript.push();
			
			
		}//end if

        //if the button location of the target location is less than the character location aka below it
		else
		{
            //move down and push the button
            mainScript.moveDown();
			mainScript.push();
			
		} //end else
		
		
		//loop through belt length
		for (int i = 0; i < beltLength; i++)
		{
            //set current distance
			curDistance = Mathf.Abs(buttonLocations[i] - charLocation);

            //if the button isnt cooling down and it is close
			if (buttonCooldowns[i] <= 0 && (beltDirections[i] == -1 || beltDirections[i] == 0))
			{
                //if the current distance is less than the minimum distance
				if (curDistance < minDistance)
				{
                    //set the minimum index to i
					minIndex = i;

                    //set the minimum distance to the current distance
					minDistance = curDistance;

                    //if the bomb speed of i is less than the bomb speed of the next one and if the bomb location of i 
					//is greater than the bomb location of the next one aka further away
                    if( (bombSpeeds[minIndex] < bombSpeeds[minIndex+1]) && (bombLocations[minIndex] > bombLocations[minIndex+1]) ){

                        //add one to the minimum index
						minIndex = i + 1;

                        
					} //end if

                    else{
                        //if the bomb location of the previous is less than the next
                        if(bombLocations[minIndex-1] < bombLocations[minIndex+1]){
                            //subtract one from min index
							minIndex = i - 1;

                            }//end if

                    }//end else
					
				}//end if
			}//end if
		}// end for loop
		
        //set the target index to the min
		targetBeltIndex = minIndex;
		
	} //end update
}
