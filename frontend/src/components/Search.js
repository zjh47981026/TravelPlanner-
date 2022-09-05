import React, { useState } from "react";
import PlacesAutocomplete from "react-places-autocomplete";

export default function Search() {
	const [address, setAddress] = useState("");
	// const [coordinates, setCoordinates] = useState({ lat: null, lng: null });
	const [placeId, setPlaceId] = useState("");
	const [showDetails, setShowDetails] = useState(false);

	const [details, setDetails] = useState();
	const [name, setName] = useState();
	const [formalAddress, setformalAddress] = useState();
	const [types, setTypes] = useState();
	const [openingHours, setOpeningHours] = useState();

	const handleSelect = async (address, placeId) => {
		// const result = await geocodeByPlaceId(placeId);
		// const coordinates = await getLatLng(result[0]);
		const details = await getDetails(placeId);
		setAddress(address);
		// setCoordinates(coordinates);
		setPlaceId(placeId);

		setDetails(details);
		setName(details.name);
		setformalAddress(details.formatted_address);
		setTypes(details.types[0]);
		setOpeningHours(details.opening_hours?.weekday_text);

		setShowDetails(true);
	};

	const handleChange = (address) => {
		setAddress(address);
		setShowDetails(false);
	};

	const getDetails = async (placeId) =>
		new Promise((resolve, reject) => {
			if (!placeId) reject("PlaceId not provided");

			try {
				new window.google.maps.places.PlacesService(document.createElement("div")).getDetails(
					{
						placeId,
						fields: ["name", "types", "opening_hours", "formatted_address"],
					},
					(details) => {
						/*
						let postcode = null;
                        details?.address_components?.forEach((entry) => {
							if (entry.types?.[0] === "postal_code") {
								postcode = entry.long_name;
							}
						});
                        */
						return resolve(details);
					}
				);
			} catch (e) {
				reject(e);
			}
		});

	return (
		<div>
			<PlacesAutocomplete value={address} onChange={handleChange} onSelect={handleSelect}>
				{({ getInputProps, suggestions, getSuggestionItemProps, loading }) => (
					<div>
						<input {...getInputProps({ placeholder: "Search here" })} size="60" />

						<div>
							{loading ? <div>loading...</div> : null}

							{suggestions.map((suggestion) => {
								const style = { backgroundColor: suggestion.active ? "#0585f5" : "#ffffff" };

								return (
									<div {...getSuggestionItemProps(suggestion, { style })} key={suggestion.placeId}>
										{suggestion.description}
									</div>
								);
							})}
						</div>
					</div>
				)}
			</PlacesAutocomplete>

			<p></p>
			<hr />

			{showDetails === true ? (
				<div>
					<p>Name: {name}</p>
					<p>Address: {formalAddress}</p>
					<p>Types: {types}</p>
					Opening Hours
					{openingHours ? "" : ": Not Available"}
					<ul>
						{openingHours
							? openingHours.map((element) => {
									return <li>{element}</li>;
							  })
							: ""}
					</ul>
				</div>
			) : null}
		</div>
	);
}
