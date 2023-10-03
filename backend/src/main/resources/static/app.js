let stompClient = null;

function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility
        = connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = '';
}

function connect() {
    stompClient = Stomp.client('ws://localhost:8080/chat');
    const headers = {
        'Authorization': 'Bearer ' + window.localStorage.getItem('token')
    }
    const chatId = document.getElementById('chat-id').value;
    stompClient.connect(headers, (frame) => {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe(`/topic/${chatId}/messages`, (messageOutput) => {
            showMessageOutput(JSON.parse(messageOutput.body));
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    const chatId = document.getElementById('chat-id').value;
    const textEl = document.getElementById('text');
    fetch('/chats/' + chatId + '/messages', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + window.localStorage.getItem('token')
        },
        body: JSON.stringify({
            'text': textEl.value
        })
    })
    // clear text input
    textEl.value = '';
}

function showMessageOutput(message) {
    const response = document.getElementById('response');
    const p = document.createElement('p');
    p.appendChild(document.createTextNode(message.sender.username + ": "
        + message.text + " (" + message.sentAt + ")"));
    response.appendChild(p);
}
    

function login() {
    const email = document.getElementById('login').value;
    const password = document.getElementById('password').value;
    const url = '/auth/token';
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            'username': email,
            'password': password
        })
    })
        .then(response => response.json())
        .then(data => {
            window.localStorage.setItem('token', data.token);
        });
}
