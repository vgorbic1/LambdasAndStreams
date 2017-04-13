## Lambdas and Streams
**Demo project**

### The Challenge:
- Analyze a bigger amount of data according to varing criteria
- Parallelize this task without explicit use of thread management, synchronization, Excecutor, of Forkjoin.

### The Solution:
- Use *parallelStream()* instead of *stream()*

**Stream** is a continuous flow of data, comparable to something like an InputStream.
The data may be emmited by different sources, such as a collection, a file or others.
The stream emits arbitraty objects. On their journey from source to target, 
the objects may be filtered, changed, transformed, collected or processed in some way.

**ParallelStream** is a several streams of the objects of the same type. The objects
are split in a parallel streams at their source.

The Stream interfaces come along with a fluent API (combining all methods via dot
notation). For processing sstreams there are two forms of pertations:
- An **Intermediate Operation** is one that is processed within the stream. Those
like `filter`, `map`, or `sorted`. They accept a stream and emit it.
- A **Terminal Operation** closes the stream. Those like `collect`, `reduce`, and
`sum`. There can be only one terminal operation.

The process does not start until the terminal operation is called. Thus, it is possible
to build the chain dynamically.


