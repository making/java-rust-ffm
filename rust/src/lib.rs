#[no_mangle]
pub extern "C" fn tak(x: i32, y: i32, z: i32) -> i32 {
    if y < x {
        tak(tak(x - 1, y, z), tak(y - 1, z, x), tak(z - 1, x, y))
    } else {
        y
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_tak_base_case() {
        // When y >= x, should return y
        assert_eq!(tak(5, 10, 0), 10);
        assert_eq!(tak(5, 5, 3), 5);
        assert_eq!(tak(0, 10, 20), 10);
    }

    #[test]
    fn test_tak_recursive_case() {
        // Classic test cases for Takeuchi function
        assert_eq!(tak(6, 2, 1), 6);
        assert_eq!(tak(10, 5, 0), 10);
        assert_eq!(tak(12, 6, 0), 12);
    }

    #[test]
    fn test_tak_small_values() {
        // When y >= x, return y
        assert_eq!(tak(1, 0, 0), 0);
        assert_eq!(tak(2, 1, 0), 2);
        assert_eq!(tak(3, 2, 1), 3);
    }

    #[test]
    fn test_tak_negative_values() {
        assert_eq!(tak(-5, 0, 0), 0);
        assert_eq!(tak(0, -5, 3), 3);
    }
}