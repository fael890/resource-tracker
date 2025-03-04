// const stompClient = new StompJs.Client(
//     { brokerURL: 'ws://localhost:8080/connection' }
// );

// stompClient.onConnect = (frame) => {
//     //setConnected(true);
//     console.log('Connected: ' + frame);
//     stompClient.subscribe('/topic/process', (message) => {
//         let processList = JSON.parse(message.body);

//         let formattedText = processList.map(
//             p => `Name: ${p.name}, 
//             CPU: ${p.cpuUsage}, 
//             RAM: ${p.memoryUsage} MB, 
//             Disk Read: ${p.diskReadUsage},
//             Disk write: ${p.diskWriteUsage}`,
//         ).join('<br>');

//         document.getElementById("time").innerHTML = processList[0].timestamp;
//         document.getElementById("process").innerHTML = formattedText;
//         document.getElementById("gpu-usage").innerHTML = processList[0].gpuUsage;
//         //console.log("Nova mensagem recebida:", message.body);
//     });
// };

// function startMonitoring() {
//     stompClient.publish({
//         destination: "/app/process",
//         body: "test"
//     });
// } 

// function connect() {
//     stompClient.activate();
// }

// function disconnect() {
//     stompClient.deactivate();
//     //setConnected(false);
//     console.log("Disconnected");
// }

// $(function () {
//     $("form").on('submit', (e) => e.preventDefault());
//     $( "#connect" ).click(() => connect());
//     $( "#disconnect" ).click(() => disconnect());
//     //$( "#monitoring" ).click(() => startMonitoring());
// });





// // stompClient.onWebSocketError = (error) => {
// //     console.error('Error with websocket', error);
// // };

// // stompClient.onStompError = (frame) => {
// //     console.error('Broker reported error: ' + frame.headers['message']);
// //     console.error('Additional details: ' + frame.body);
// // };

// // function setConnected(connected) {
// //     $("#connect").prop("disabled", connected);
// //     $("#disconnect").prop("disabled", !connected);
// //     if (connected) {
// //         $("#conversation").show();
// //     }
// //     else {
// //         $("#conversation").hide();
// //     }
// //     $("#greetings").html("");
// // }

// // function connect() {
// //     stompClient.activate();
// // }



// // function sendName() {
// //     stompClient.publish({
// //         destination: "/app/hello",
// //         body: JSON.stringify({'name': $("#name").val()})
// //     });
// // }

// // function showGreeting(message) {
// //     $("#greetings").append("<tr><td>" + message + "</td></tr>");
// // }

// // $(function () {
// //     $("form").on('submit', (e) => e.preventDefault());
// //     $( "#connect" ).click(() => connect());
// //     $( "#disconnect" ).click(() => disconnect());
// //     $( "#send" ).click(() => sendName());
// // });