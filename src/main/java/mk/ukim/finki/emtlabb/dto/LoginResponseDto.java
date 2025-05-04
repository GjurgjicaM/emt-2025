package mk.ukim.finki.emtlabb.dto;

public record LoginResponseDto(
        String token
) {
    @Override
    public String token() {
        return token;
    }
}

