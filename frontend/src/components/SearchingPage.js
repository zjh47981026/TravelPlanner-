import React from "react";
import { Layout } from "antd";
import MapLoader from "./MapLoader";
import Search from "./Search";
import { addPlan } from "../utils";

const { Header, Content, Sider } = Layout;

function SearchingPage() {
	return (
		<Layout>
			<Sider width={500} className="site-layout-background"></Sider>
			<Layout style={{ padding: "10px" }}>
				<Content
					className="site-layout-background"
					style={{
						padding: 5,
						margin: 0,
						height: 800,

						overflow: "auto",
					}}
				>
					<MapLoader />
				</Content>
				<Layout style={{ padding: "10px" }}>
					<Search />
				</Layout>
			</Layout>
		</Layout>
	);
}

export default SearchingPage;
