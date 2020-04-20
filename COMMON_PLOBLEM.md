# Common problem
There's some common problem when you try to use MVI framework.  
In this article, get a grip on those situations. And figure out solutions.  

---

### Single Event
There are many single events on Android. 
Typical examples are toast, snackbar, navigation, etc.
From the point of view of MVI, there is no perfect answer.  
In some cases, a single event may be considered a state or side effect. 
But I decided to solve this problem by introducing a new concept. The **ViewEffect**.  

ViewEffect works based on SingleLiveEvent. 
That's why you don't need additional elements such as interface, and it's just a single event that is detected by Activity/Fragment.
Here's an example.
```kotlin
whenEvent<GithubEvent.UserLoadFailed> {
    viewEffect(GithubViewEffect.ShowSnackbar(it.errorMessage))
    noChange()
}
```
NoChange() means continuing an existing state without creating a new state.
ViewEffect() generates a single event by parameter.

---
### Rendering same data
View detects when a new state is created and performs a render(). Even if the data are the same as before.
It's not a problem at all in the case of TextView. 
But in the case of RecyclerView, the flickering phenomenon occurred. This is unacceptable in terms of user experience. There are two solutions.  

1. ListAdapter
This is a way that can be used as a RecyclerView limitation.
The ListAdapter extends RecyclerView.Adapter, and internally uses DiffUtil to notify only those parts of the data that have changed. 
Therefore, it can address flickering phenomena and provide a natural user experience.  

2. LiveData.distinctUntilChanged()
DistrictUntilChanged() is an extension of LiveData. Ignore changes until data changes.
```kotlin
val users: LiveData<List<UserModel>> = currentState.map { it.users }.distinctUntilChanged()
```
After you define above, you can detect changes in users. This way is not limited to RecyclerView, so it can be useful.
