# UDP Sockets
*UDP Sockets* is a **Client-Server Application** developed as part of the course "[Redes de Ordenadores]()" in the Telecommunications Engineering Degree at the Universidad de Vigo (2020 - 2021).

## About The Project
This project implements a simple client-server system where the client sends an integer to the server via UDP, a predefined value is added, and the result is sent back to the client, demonstrating the basics of socket programming and datagram communication.

The project features:
- UDP-based communication without persistent connections.
- Basic arithmetic operation to simulate processing in the server.
- Timeout handling to prevent indefinite waiting.
- Error detection for invalid input, socket failures, and timeouts.

## How To Run
### Compilation
Make sure you have a [Java JDK](https://www.oracle.com/java/technologies/downloads/) installed on your system. Then compile all Java classes and generate the `.class` files with:

```bash
javac -d bin src/*.java
```

This command creates the compiled files inside the `bin/` directory.

### Execution
Once compiled, you can run the system with:
#### Server
```bash
java -cp bin plusd <port> <N>
```

| Option | Description | Example |
|--------|-------------|---------|
| `port` | Specifies the UDP port where the server listens | `5000` |
| `N` | Specifies the number to be added | `10` |

##### Example
```bash
java -cp bin plusd 5000 10
```

#### Client
```bash
java -cp bin plus <server_ip> <server_port> <M> <timeout>
```
| Option | Description | Example |
|--------|-------------|---------|
| `server_ip` | Specifies the server IP address | `127.0.0.1` |
| `server_port` | Specifies the UDP port where the server is listening | `5000` |
| `M` | Specifies the number to be sent | `5` |
| `timeout` | Specifies the maximum waiting time (in seconds) for a response | `2` |

##### Example
```bash
java -cp bin plus 127.0.0.1 5000 5 2
```

## About The Code
There is no dedicated documentation for this project. Inspect the code for a deeper understanding of the system and how it works.