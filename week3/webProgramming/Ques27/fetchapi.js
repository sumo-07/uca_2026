// Create a Web Worker & webworker.js file gets executed
const worker = new Worker("webworker.js");

// Receive data from worker
worker.onmessage = function (event) {
    const data = event.data;

    if (data.error) {
        console.error("Error:", data.error);
        return;
    }

    data.forEach(user => {
        console.log(`${user.name} - ${user.status}`);
    });
};

// Handle worker errors
worker.onerror = function (error) {
    console.error("Worker Error:", error.message);
};