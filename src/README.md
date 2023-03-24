# LRU-Cache-Memory

This is an implementation of an LRU (Least Recently Used) Cache Memory using a combination of a hash map and a doubly linked list data structure. This implementation is designed to provide efficient and fast memory caching for applications.

## How It Works:

The LRU Cache Memory works by using a hash map to store key-value pairs and a doubly linked list to keep track of the most recently used items. Each item in the cache has a corresponding node in the doubly linked list, which keeps track of the order in which items were accessed. When an item is accessed, it is moved to the front of the list, indicating that it was the most recently used item.

If the cache becomes full and a new item needs to be added, the least recently used item is removed from the cache.

This implementation provides fast retrieval of cached items using the hash map and efficient cache management using the doubly linked list.

## Data Structures Implemented:

* Hash Map
* Doubly Linked List

## Features:

* Efficient cache management,ensuring that the most recently used items are quickly accessible.
* Fast retrieval of cached items,with O(1) Time Complexity for cache operations.
* Customizable capacity, the capacity of the cache can be customized to suit the needs of the application.

## Results:

dataset: dataset-1000/data-1000.dat
requests: dataset-1000/requests-10000.dat
cache size: 100 items (10% of all items)
output:
---------------------------------------------------
Read 10000 items in 3954 ms
Stats: lookups 10000, hits 1014

***************************************************

dataset: dataset-5000/data-5000.dat
requests: dataset-5000/requests-100000.dat
cache size: 500 items (10% of all items)
output:
---------------------------------------------------
Read 100000 items in 111044 ms
Stats: lookups 100000, hits 1994