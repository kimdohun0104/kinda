# Kinda official guideline

This is a guideline for packaging, naming and structuring with Kinda framework. Framework will update continuously following to this guideline. These are very simple, so make sure to follow it.

`But this is just a recommendation. Of course, it works even you don't follow this guideline.`

<br>

### Definition of State, Event, SideEffect and ViewEffect

State, Event, SideEffect, ViewEffect is core component in Kinda. To make your application work, you will need all of them.

But if you make file each of them, it's going to be hard for find certain file. So I recommend put all of them in one file and name it `<Example>State`. Place it exactly in the following order.

<img src="https://user-images.githubusercontent.com/36754680/79632626-80db6f80-819b-11ea-83df-e6c4cf51dbde.png" style="zoom:80%;" />

Now when you open this file, you might know what happens in this screen. This small habit can improve readability.

<br>

### Packaging

This is very crucial point. Activity, State, ViewModel file must placed in same package. Additional class like adapters, models should be placed in new package.

![](https://user-images.githubusercontent.com/36754680/79633122-72db1e00-819e-11ea-9bb6-aff61078ec35.png)

This is bad example. Because it has api, bindingAdapter, listAdapter and model. You can fix this like below picture.

![](https://user-images.githubusercontent.com/36754680/79633167-c9485c80-819e-11ea-94ea-beac0b94b33c.png)

This is way better than before. Api internal is frequently used in other functions, so it is taken to a different package. But remind it's just a recommendation. 

I'm considering Kinda plugin that automatically generate files. It will generate files following this packaging rule. It will save your time, but additional time may occur if the above structure is not used.

<br>

### Initial event

When you need to `intent` initially, don't call it at Activity/Fragment. Like this:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
	super.onCreate(savedInstanceState)
	viewModel.intent(GithubEvent.AttemptUserLoad)
}
```

There is a more elegant way. You can call `intent` at ViewModel's init scope. Like this:

```kotlin
class GithubViewModel(private val githubApi: GithubApi) :
    KindaViewModel<GithubState, GithubEvent, GithubSideEffect, GithubViewEffect>() {

    init {
        intent(GithubEvent.AttemptUserLoad)
    }
```

<br>
