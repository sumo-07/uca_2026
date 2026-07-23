const usersDiv = document.getElementById("users");

// Create Web Worker & Execute webworker.js file
const worker = new Worker("webworker.js");

// Receive data from worker
worker.onmessage = function (event) {

    const data = event.data;

    usersDiv.innerHTML = "";

    // Error received
    if (data.error) {
        usersDiv.innerHTML = `<p>${data.error}</p>`;
        return;
    }

    // No users
    if (data.length === 0) {
        usersDiv.innerHTML = "<p>No users found</p>";
        return;
    }

    // Display users
    data.forEach(user => {

        const p = document.createElement("p");

        p.className = `user ${user.status.toLowerCase()}`;

        p.textContent = `${user.name} - ${user.status}`;

        usersDiv.appendChild(p);
    });

};

// Worker error
worker.onerror = function (error) {
    usersDiv.innerHTML = `<p>${error.message}</p>`;
};