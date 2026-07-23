async function fetchUsers() {
    try {
        const response = await fetch("https://gorest.in/public/v2/users");

        if (!response.ok) {
            throw new Error("Failed to fetch users");
        }

        const result = await response.json();

        if (!result || result.length === 0) {
            throw new Error("No users returned from the API");
        }

        const users = result.map(user => ({
            name: `${user.name}`,
            status: user.status == 'active' ? "Active" : "Inactive"
        }));

        // Send data back to main thread
        postMessage(users);

    } catch (error) {
        // Send error back to main thread
        postMessage({
            error: error.message
        });
    }
}

// Start fetching
fetchUsers();