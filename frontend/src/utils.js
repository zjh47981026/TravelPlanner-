const domain = "http://localhost:8080";

export const login = (credential) => {
	const loginUrl = `${domain}/authenticate`;
	return fetch(loginUrl, {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(credential),
	}).then((response) => {
		if (response.status !== 200) {
			throw Error("Fail to login");
		}

		return response.json();
	});
};

export const register = (credential) => {
	const registerUrl = `${domain}/register`;
	return fetch(registerUrl, {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(credential),
	}).then((response) => {
		if (response.status !== 200) {
			throw Error("Fail to register");
		}
	});
};

export const getPlans = () => {
	const authToken = localStorage.getItem("authToken");
	const listPlansUrl = `${domain}/plans`;

	return fetch(listPlansUrl, {
		headers: {
			Authorization: `Bearer ${authToken}`,
		},
	}).then((response) => {
		if (response.status !== 200) {
			throw Error("Fail to get plan list");
		}

		return response.json();
	});
};

export const deletePlan = (planId) => {
	const authToken = localStorage.getItem("authToken");
	const deletePlanUrl = `${domain}/plans/${planId}`;

	return fetch(deletePlanUrl, {
		method: "DELETE",
		headers: {
			Authorization: `Bearer ${authToken}`,
		},
	}).then((response) => {
		if (response.status !== 200) {
			throw Error("Fail to delete plan");
		}
	});
};

export const addPlan = (data) => {
	const authToken = localStorage.getItem("authToken");
	const addPlanUrl = `${domain}/plans`;

	return fetch(addPlanUrl, {
		method: "POST",
		headers: {
			Authorization: `Bearer ${authToken}`,
			"Content-Type": "application/json",
		},
		body: JSON.stringify(data),
	}).then((response) => {
		if (response.status !== 200) {
			throw Error("Fail to add plan");
		}
	});
};

export const getPlanStops = (data) => {
	const authToken = localStorage.getItem("authToken");
	const getPlanStopsUrl = `${domain}/plans/stops`;

	return fetch(getPlanStopsUrl, {
		headers: {
			Authorization: `Bearer ${authToken}`,
			"Content-Type": "application/json",
		},
		body: JSON.stringify(data),
	}).then((response) => {
		if (response.status !== 200) {
			throw Error("Fail to get stops in this plan");
		}

		return response.json();
	});
};
