# secApp
Simple app for managing users and groups. It uses the container specific (glassfish) APIs for authorization and authentication.

A guest can sign up with a username (must be unique) and a password. This user doesn't belong to any groups as long as the administrator will add to a group.
In this application groups are fixed, and not modifiable.
A simple user can only change own password. A superuser can delete a user and change its groups.
