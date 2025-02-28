# docker-workshop
git clone https://github.com/stardusts777/docker-workshop.git

# Example API Calls using cURL
```bash
# Create a new user
curl -X POST "http://localhost:8080/api/users" -H "Content-Type: application/json" -d '{"name": "John Doe", "email": "john@example.com"}'

# Get all users
curl -X GET "http://localhost:8080/api/users"

# Get user by ID
curl -X GET "http://localhost:8080/api/users/1"

# Update user by ID
curl -X PUT "http://localhost:8080/api/users/1" -H "Content-Type: application/json" -d '{"name": "John Smith", "email": "johnsmith@example.com"}'

# Partially update user by ID
curl -X PATCH "http://localhost:8080/api/users/1" -H "Content-Type: application/json" -d '{"email": "newemail@example.com"}'

# Delete user by ID
curl -X DELETE "http://localhost:8080/api/users/1"
```
