# Hoover

RESTful API that replicates an Hoover's robot behaviour which purpose is clean patches in a room.

Room size, initial position, patches coordinates and the hoover instructions are provided from the user.

Technologies used are:
  - Spring Boot
  - Junit
  - Maven

## Usage

To start the application this command needs to be run :
```
  mvn spring-boot:run
```

Once the application is started, next step is make a call to the endpoint that process the hoover transition.

  ### Required parameters in the request body are:
  
  - patches : Patches coordinates to clean
  - initialPosition : Hoover's initial coordinates
  - roomSize : Coordinates of the furthest point of the room
  - instructions : Instructions expressed using first capitalised letter of the desired direction(NORTH,SOUTH,WEST,EAST)

    - Using cURL:
    ```sh
          curl -X POST \
          http://localhost:8080/hoover/run \
          -H 'content-type: application/json' \
          -d '{
                  "roomSize": {
                    "x": 5,
                    "y": 5
                  },
                  "initialPosition": {
                    "x": 1,
                    "y": 2
                  },
                  "patches": [
                    {
                      "x": 1,
                      "y": 0
                    },
                    {
                      "x": 2,
                      "y": 2
                    },
                    {
                      "x": 2,
                      "y": 3
                    }
                  ],
                  "instructions": "NNESEESWNWW"
              }'
    ```
  
    - Response for the above request:
  
    ```sh
          {
              "finalPosition": {
                  "x": 1,
                  "y": 3
              },
              "patches": 1
          }
      ```
