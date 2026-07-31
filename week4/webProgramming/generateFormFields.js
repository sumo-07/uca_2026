const fields = [
  "First Name",
  "Last Name",
  "Email"
];

function generateForm(fieldsList) {
  return fieldsList.map(field => `  <label>${field}</label> \n  <input type="text">`).join('\n');
}

console.log(generateForm(fields));
