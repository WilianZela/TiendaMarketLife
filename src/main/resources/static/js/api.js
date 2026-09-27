async function apiRequest(url, method, body) {
    const options = {
        method,
        headers: { "Content-Type": "application/json" }
    };
    if (body) {
        options.body = JSON.stringify(body);
    }

    const response = await fetch(url, options);
    let data = null;
    try {
        data = await response.json();
    } catch (e) {
        data = null;
    }

    if (!response.ok) {
        const message = data && data.error ? data.error : "Ocurrió un error";
        throw new Error(message);
    }

    return data;
}
