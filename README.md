# kodink

Is an experiment library to create easy user interface using kotlin and redux architecture.

## Getting Started

The develop of the current project is using the IntelliJ IDEA. Clone the repo and open with IntelliJ

### Prerequisites

TODO...

### Installing

Create a State

```kotlin
data class CounterState(val counter: Int = 0) : State
```

Create a Action
```kotlin
data class CounterAction(override val type: String) : Action
```

Create a Reducer function

```kotlin
fun counterReducer(state: CounterState, action: Action): CounterState {
    return when (action.type) {
        "INCREMENT" -> CounterState(state.counter + 1)
        "DECREMENT" -> CounterState(state.counter - 1)
        else -> state
    }
}
```

Add the Reducer to the store

```kotlin
store.addReducer(::counterReducer, CounterState())
```

Dispatch an Action

```kotlin
Provider.store.dispatch(CounterAction("INCREMENT")) 
```

See the current State

```kotlin
println(store.getState())
```

## Running the tests

TODO...

### Break down into end to end tests

TODO...

### And coding style tests

TODO...

## Deployment

TODO...

## Built With

TODO...

## Contributing

TODO...

## Versioning

TODO...

## Authors

* **David Barrantes** - *Initial work* - [Dfenix](https://github.com/dfenix)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Thanks

* [Kotlin](https://kotlinlang.org/) 
* readme.md template by [PurpleBooth](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2)
* [Redux](https://github.com/reactjs/redux)
* Atomic design by [Brad Frost](http://bradfrost.com/blog/post/atomic-web-design/)

