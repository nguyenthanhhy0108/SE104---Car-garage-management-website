async function getAllParts() {
    try {
        const response = await $.ajax({
            url: '/get-all-parts',
            method: 'GET',
            dataType: 'json'
        });
        $('#response').html(response);
        return response;
    } catch (error) {
        console.error('Error:', error);
        throw new Error('Error: ' + error);
    }
}

getAllParts().then(parts => {
    // console.log(parts);
    // console.log(parts[0])
}).catch(error => {
    console.error('Error:', error);
});


async function getAllServices() {
    try {
        const response = await $.ajax({
            url: '/get-all-services',
            method: 'GET',
            dataType: 'json'
        });
        $('#response').html(response);
        return response;
    } catch (error) {
        console.error('Error:', error);
        throw new Error('Error: ' + error);
    }
}

getAllServices().then(services => {
    // console.log(services);
    // console.log(services[0])
}).catch(error => {
    console.error('Error:', error);
});


async function getAllReceipts() {
    try {
        const response = await $.ajax({
            url: '/get-all-receipt',
            method: 'GET',
            dataType: 'json'
        });
        $('#response').html(response);
        return response;
    } catch (error) {
        console.error('Error:', error);
        throw new Error('Error: ' + error);
    }
}

getAllReceipts().then(receipts => {
    console.log(receipts);
}).catch(error => {
    console.error('Error:', error);
});
