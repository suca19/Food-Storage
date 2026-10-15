# Food Storage

A Java console application that models a fast-food restaurant's storage unit. The unit has a **front** side and a **rear** side. The chef can store food in two ways:

1. **Same end:** add and remove at the same side. The last item in is the first out (LIFO, like a stack).
2. **Opposite ends:** add at the rear and remove from the front. The first item in is the first out (FIFO, like a queue).

Both scenarios are supported, and you can switch between them at runtime.

## Features

- Add a food item (Burger, Pizza, Fries, Sandwich or Hotdog) with its weight in grams and a best-before date. The time it was added is recorded automatically.
- Remove an item according to the current mode (LIFO or FIFO).
- Show the name of the item at the top (the next one to be removed).
- Display all items from front to rear.
- Search for an item by name (case-insensitive).
- Switch between the same-end and opposite-end modes.
- Exit the application.

## Validation and error handling

| Input / situation | Rule |
|---|---|
| Menu option, food type | Must be a whole number in range; asks again otherwise |
| Weight | Whole number from 1 to 5000 g |
| Best-before date | Format `yyyy-MM-dd`, between today and 14 days ahead |
| Adding when full | `StorageFullException`: "Storage is full" |
| Removing, peeking, displaying or searching when empty | `StorageEmptyException`: "Storage is empty" |

Errors are caught in the menu loop, so the application never crashes on bad input or an empty storage.

## Data structure: circular array deque

`FoodStorage` is a **double-ended queue (deque)** built on a fixed array of 8 slots, used as a ring buffer:

- `head` points to the front item, and `size` counts the stored items.
- The rear position is `(head + size - 1) % 8`, so indexes wrap around the array and nothing is ever shifted.

**Why a deque?** A stack only works at one end and a queue only works at opposite ends. A deque supports both in O(1), so one structure covers both scenarios in the brief. The fixed-size array matches the physical storage unit's limited capacity.

### Time complexity

| Operation | Method(s) | Time | Reason |
|---|---|---|---|
| Add | `addRear` | O(1) | Index computed directly, no shifting |
| Remove | `removeFront`, `removeRear` | O(1) | Only `head`/`size` change |
| Peek (show top) | `peekFront`, `peekRear` | O(1) | Direct array access |
| Display | `getAllFoodItems` | O(n) | Visits every item |
| Search | `search` | O(n) | Linear scan by name |
| isEmpty / isFull / size | — | O(1) | Compare a counter |

Space: O(1), because the array is fixed at 8 slots regardless of use.

### Advantages and limitations

- **Advantages:** O(1) add and remove at both ends, predictable memory use, and one class serves both scenarios.
- **Limitations:** the capacity is fixed (no resizing), search is linear, and data is not saved when the program closes.
-The custom exceptions use **inheritance**: they extend `RuntimeException`.

## How to run

Requires JDK 17 or later (developed with JDK 21).

```bash
javac -d out $(find src -name "*.java")
java -cp out app.Main      # run the application
java -cp out app.Scratch   # run the tests
```

You can also open the folder in VS Code or NetBeans and run `app.Main`.

## Testing

`Scratch.java` checks:

1. Empty-storage errors for remove, peek and display
2. FIFO order (add rear, remove front)
3. LIFO order (add rear, remove rear)
4. Search: found and not found
5. Full storage: the 9th item is rejected
6. Circular wrap-around keeps the correct order

Each line of output prints the expected value next to the actual one.
