package mk.ukim.finki.emtlabb.dto;

public record LoginUserDto(String username, String password) {
    @Override
    public String username() {
        return username;
    }

    @Override
    public String password() {
        return password;
    }
}

