package main

import (
	"fmt"
	"log"
	"net"
	"net/rpc"
	"strconv"
)

type Service struct {
	cnt int
}

// 有点像 给结构体一个函数的感觉
func (service *Service) Hello(id int, reply *string) error {

	fmt.Println(id)
	*reply = "hello : id = " + strconv.Itoa(id) + "分配任务号 : " + strconv.Itoa(service.cnt)
	service.cnt--
	return nil
}

func main() {

	service := new(Service)
	service.cnt = 100
	rpc.RegisterName("Service", service)
	// 建立tcp链接 并监听1234端口
	l, err := net.Listen("tcp", ":1234")
	if err != nil {
		log.Fatal("tcp error", err)
	}
	for {
		conn, err := l.Accept()
		if err != nil {
			log.Fatal("accept error", err)
		}
		go rpc.ServeConn(conn)
	}

}
