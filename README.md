
# Advanced FAB s

This is kind of a FloatingActionButton includes Animations .
## Features

- FAB: Curve fab, Rectangle feb
- Easy Set up
- Items setup : Icons, colors, text  
- Free Position : Put anywhere in Parent Layout
- Get Running Function :Repeatdly to do
- Animations: Enter and Exit
- Colors : Changing colors from / to 


## 📚Installation

Install my-project with https://jitpack.io/

```bash
  dependencies {
	        implementation 'com.github.Mori-hub:Advanced_FAB:1.0.0'
	}
```
  [![](https://jitpack.io/v/Mori-hub/Advanced_FAB.svg)](https://jitpack.io/#Mori-hub/Advanced_FAB)
  
## 🧰Usage

```javascript
    // Call Lib Rectangle feb
   val rectangle = Rectangle(this, R.id.mainParent)
    // Call Lib Curve fab
	 val curve = Curve(this, R.id.mainParent,findViewById(R.id.floatingActionButton))
		
        
```
## Explain
* **Parent Layout** : This must be a Layout and put anywhere
* **null** : For Running Function and Finish Function
* **Show** : When you call main method, the layout will attach to the window 
* **SetItems** : For set as you need
## Structure
```javascript
fun setItems(
        icons: ArrayList<Int>,
        colors: ArrayList<Int>,
        textIn: ArrayList<String>,
        textColor: Int,
        runningFunctions: ArrayList<(() -> Unit)?>
    )
```   
## Examples
```javascript
rectangle.setItems(
            //Icons from Res
            arrayListOf(
                android.R.drawable.ic_media_play, 
                android.R.drawable.ic_input_add,
                android.R.drawable.btn_star_big_on, 
                android.R.drawable.ic_delete
            ),
            // Colors of row
            arrayListOf(
                Color.parseColor("black"),
                Color.parseColor("#8A351A"),
                Color.parseColor("#FF5722"),
                Color.parseColor("#9C27B0")
            ),
            // Text of each row
            arrayListOf("One", " two", "see it", "fun"),
            // Text Color
            Color.WHITE,
            // OnClick must be in {}
            arrayListOf(
                null,
                {Toast.makeText(applicationContext, "Hi", Toast.LENGTH_SHORT).show()
                    println("Hi there!")
                },
                null,
                null
            )
        )
```

## Next One
 This menu attachs to a floatingActionButton, so must be as a parameter
 ## Structure
```javascript
Curve(activity: Activity, parentLayout: Int, targetButton: View)

setItems(
        icons: ArrayList<Int>,
        colors: ArrayList<Int>,
        runningFunctions: ArrayList<(() -> Unit)?>
    ) 
```   
## Examples
```javascript
   val curve = Curve(this, R.id.mainParent,findViewById(R.id.floatingActionButton))

        curve.setItems(
            //Icons from Res
            arrayListOf(
                android.R.drawable.ic_media_play, android.R.drawable.ic_input_add
                , android.R.drawable.btn_star_big_on, android.R.drawable.ic_delete
            ),
            // Colors of background icons
            arrayListOf(Color.parseColor("black"),
                Color.parseColor("#8A351A"),
                Color.parseColor("#FF5722"),
                Color.parseColor("#9C27B0")),
            // OnClick must be in {}
            arrayListOf(null,
                { Toast.makeText(applicationContext, "Hi", Toast.LENGTH_SHORT).show()
                    println("Hi there!")
                },
                null,
                null))
```

## Screenshots && Gifs
<img src="https://user-images.githubusercontent.com/53067774/165587883-c1462fa3-3f79-47e0-bd0b-53e13de5b2e8.jpg" width="15%"></img> <img src="https://user-images.githubusercontent.com/53067774/165587911-8dbe4371-e393-42e0-9f28-a4279563f033.jpg" width="15%"></img> <img src="https://user-images.githubusercontent.com/53067774/165588195-760a3584-4db6-40a2-b15e-df3363db76fd.gif" width="15%"></img> <img src="https://user-images.githubusercontent.com/53067774/165588225-275375ba-6879-4496-b662-fc6a15649acc.gif" width="15%"></img> 



## 🖐Important Notic
Please add parameters due to number of icons, otherwise the app will crash and close.

## 🚀 About Me
I'm a full stack developer...


## 🛠 Skills
Java, Kotlin, CSS....


## Tech Stack

**Important:** For Gradle 7.2 & jitpack.io Please use this way : https://stackoverflow.com/a/71603699/12272687

**Update:** March 2022


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/Mori-hub)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/)
[![twitter](https://img.shields.io/badge/twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/)
[![Google](https://img.shields.io/badge/My%20Apps-Google%20Play%20Store-green?style=for-the-badge&logo=googleplay)](https://play.google.com/store/search?q=pub:Smart%20rabit&c=apps)

## Feedback

If you have any feedback, please reach out to us at SR-App@outlook.com


## License

[MIT](https://choosealicense.com/licenses/mit/)

