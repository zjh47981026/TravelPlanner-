import React, { Component } from "react";
import { Form, Button, Input, Space, Checkbox, message } from "antd";
import { UserOutlined } from "@ant-design/icons";
import { login, register } from "../utils";

class LoginPage extends Component {
	formRef = React.createRef();
	state = {
		loading: false,
	};

	handleLogin = async () => {
		const formInstance = this.formRef.current;

		try {
			await formInstance.validateFields();
		} catch (error) {
			return;
		}

		this.setState({
			loading: true,
		});

		try {
			const resp = await login(formInstance.getFieldsValue(true));
			this.props.handleLoginSuccess(resp.token);
		} catch (error) {
			message.error(error.message);
		} finally {
			this.setState({
				loading: false,
			});
		}
	};

	handleRegister = async () => {
		const formInstance = this.formRef.current;

		try {
			await formInstance.validateFields();
		} catch (error) {
			return;
		}

		this.setState({
			loading: true,
		});

		try {
			await register(formInstance.getFieldsValue(true));
			message.success("Register Successfully");
		} catch (error) {
			message.error(error.message);
		} finally {
			this.setState({
				loading: false,
			});
		}
	};

	render() {
		return (
			<div style={{ width: 500, margin: "20px auto" }}>
				<Form ref={this.formRef}>
					<Form.Item
						name="username"
						rules={[
							{
								required: true,
								message: "Please input your Username!",
							},
						]}
					>
						<Input
							disabled={this.state.loading}
							prefix={<UserOutlined className="site-form-item-icon" />}
							placeholder="Username"
						/>
					</Form.Item>
					<Form.Item
						name="password"
						rules={[
							{
								required: true,
								message: "Please input your Password!",
							},
						]}
					>
						<Input.Password disabled={this.state.loading} placeholder="Password" />
					</Form.Item>
				</Form>
				<Space>
					<Button onClick={this.handleLogin} disabled={this.state.loading} shape="round" type="primary">
						Log in
					</Button>
					<Button onClick={this.handleRegister} disabled={this.state.loading} shape="round" type="primary">
						Register
					</Button>
				</Space>
			</div>
		);
	}
}

export default LoginPage;
