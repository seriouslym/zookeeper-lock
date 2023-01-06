package main

import (
	"fmt"
	"log"
	"net/rpc"
	"strconv"
)

func test() {
	for i := 0; i < 50; i++ {
		fmt.Println("子runtime" + strconv.Itoa(i))
		client, err := rpc.Dial("tcp", "localhost:1234")
		if err != nil {
			log.Fatal("connect err", err)
		}
		var reply string
		err = client.Call("Service.Hello", i, &reply)
		if err != nil {
			log.Fatal(err)
		}
		log.Println(reply)
	}
}
func main() {
	done := make(chan struct{})
	go func() {
		test()
		done <- struct{}{}
	}()
	for i := 0; i < 50; i++ {
		fmt.Println(i)
	}
	<-done
}
