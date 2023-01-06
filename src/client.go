package main

import (
	"fmt"
	"log"
	"net/rpc"
)

func test() {
	for i := 0; i < 1000; i++ {
		client, err := rpc.Dial("tcp", "localhost:1234")
		if err != nil {
			log.Fatal("connect err", err)
		}
		var reply string
		err = client.Call("Service.Hello", i, &reply)
		if err != nil {
			log.Fatal(err)
		}
		fmt.Println(reply)
	}
}
func main() {
	fmt.Println(222)
	go test()
}
