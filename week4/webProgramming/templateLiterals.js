const users = [
    { name: "Alice", age: 53 },
    { name: "Tim", age: 74 }
];

function generateTable(userList) {
    return `<table>
${userList.map(user => `  <tr>
    <td>${user.name}</td>
    <td>${user.age}</td>
  </tr>`).join('\n')}
</table>`;
}

console.log(generateTable(users));