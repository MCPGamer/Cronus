var data = [];
var selectedEntry = null;

resetForm();

// fetch all entries and show them in the table
getAllEntries().then(entries => {
	data = entries;
	renderData();
});


function onFormSubmit() {
	var name = document.getElementById("name").value;
	var done = document.getElementById("done").value;
	var until = document.getElementById("until").value + "T00:00:00";

	/*if (!checkIn || !checkOut) {
		// data is invalid
		return;
	}*/

	var entry = { entryText: name, done: done, dueDate: until };
	if (selectedEntry == null) {
		createEntry(entry).then(createdEntry => {
			data.push(createdEntry);
			renderData();
			resetForm();
		});
	} else {
		updateEntry(selectedEntry.id, entry).then(updatedEntry => {
			selectedEntry.entryText = updatedEntry.entryText;
			selectedEntry.done = updatedEntry.done;
			selectedEntry.dueDate = updatedEntry.dueDate;
			renderData();
			resetForm();
		});
	}
}

function resetForm() {
	document.getElementById("name").value = "";
	document.getElementById("done").value = false;
	document.getElementById("until").value = new Date();
	selectedEntry = null;
}

function onEdit(index) {
	selectedEntry = data[index];
	document.getElementById("checkIn").value = selectedEntry.checkIn;
	document.getElementById("checkOut").value = selectedEntry.checkOut;

	document.getElementById("name").value = selectedEntry.entryText;
	document.getElementById("done").value = selectedEntry.done;
	document.getElementById("until").value = selectedEntry.dueDate;
}

function onDelete(index) {
	if (confirm('Are you sure to delete this record ?')) {
		selectedEntry = data[index];
		deleteEntry(selectedEntry.id);
		data.splice(index, 1);
		renderData();
		resetForm();
	}
}

function renderData() {
	var table = document.getElementById("entryList").getElementsByTagName('tbody')[0];

	// delete existing rows
	for (var i = table.rows.length - 1; i >= 0; i--) {
		table.deleteRow(i);
	}

	// add all rows from the data array
	for (var i = 0; i < data.length; i++) {
		var entry = data[i];
		var newRow = table.insertRow(table.length);
		cell1 = newRow.insertCell(0);
		cell1.innerHTML = entry.id;
		cell1 = newRow.insertCell(1);
		cell1.innerHTML = entry.entryText;
		cell1 = newRow.insertCell(2);
		cell1.innerHTML = entry.dueDate;
		cell1 = newRow.insertCell(3);
		cell1.innerHTML = `<input type="checkbox" class="done-cb" ` + (entry.done ? "checked" : "") + ` >`;
		cell2 = newRow.insertCell(4);
		cell2.innerHTML = `<a onClick="onEdit(${i})">Edit</a>
						   <a onClick="onDelete(${i})">Delete</a>`;
		//localStorage.setItem("token", token)
	}
}						   
