async function fetchUsers() {

    try {

        const response = await fetch("https://gorest.in/public/v2/users");

        if (!response.ok) {
            throw new Error("Failed to fetch users.");
        }

        const result = await response.json();

        if (result.length === 0) {
            throw new Error("No users found.");
        }

        const users = result.map(user => ({
            name: user.name,
            status: user.status === "active" ? "Active" : "Inactive"
        }));

        postMessage(users);

    } catch (error) {

        postMessage({
            error: error.message
        });

    }

}

fetchUsers();