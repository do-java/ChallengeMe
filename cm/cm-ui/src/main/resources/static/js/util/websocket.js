import SockJS from 'sockjs-client'
import { Stomp } from '@stomp/stompjs'

var stompClient = null;
const echoHandlers = []

export function addEchoHandler(handler) {
    echoHandlers.push(handler)
}

export function connect() {
    var socket = new SockJS('/cm-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/echo', function (message) {
        	echoHandlers.forEach(handler => handler(JSON.parse(message.body)))
        });
    });
}

export function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

// Unused
export function sendEcho(echoMessage) {
    stompClient.send("/app/echoMessage", {}, JSON.stringify({'value': 'echoMessage'}));
}