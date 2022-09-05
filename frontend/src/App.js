import { Layout, Dropdown, Menu, Button } from "antd";
import { UserOutlined } from "@ant-design/icons";
import React from "react";
import LoginPage from "./components/LoginPage";
import SearchingPage from "./components/SearchingPage";

const { Header, Content } = Layout;

class App extends React.Component {
	state = {
		authed: false,
	};

	componentDidMount() {
		const authToken = localStorage.getItem("authToken");
		this.setState({
			authed: authToken !== null,
		});
	}

	handleLoginSuccess = (token) => {
		localStorage.setItem("authToken", token);
		this.setState({
			authed: true,
		});
	};

	handleLogout = () => {
		localStorage.removeItem("authToken");
		this.setState({
			authed: false,
		});
	};

	renderContent() {
		if (!this.state.authed) {
			return <LoginPage handleLoginSuccess={this.handleLoginSuccess} />;
		}

		return <SearchingPage />;
	}

	userMenu = (
		<Menu>
			<Menu.Item key="logout" onClick={this.handleLogout}>
				Logout
			</Menu.Item>
		</Menu>
	);

	render() {
		return (
			<Layout style={{ height: "100vh" }}>
				<Header style={{ display: "flex", justifyContent: "space-between", backgroundColor: "#34ebd5" }}>
					<div style={{ fontSize: 20, fontWeight: 600, color: "white" }}>Travel Planner</div>
					{this.state.authed && (
						<div>
							<Dropdown trigger="click" overlay={this.userMenu}>
								<Button icon={<UserOutlined />} shape="circle" />
							</Dropdown>
						</div>
					)}
				</Header>
				<Content style={{ height: "calc(100% - 64px)", margin: 20, overflow: "auto" }}>
					{this.renderContent()}
				</Content>
			</Layout>
		);
	}
}

export default App;
